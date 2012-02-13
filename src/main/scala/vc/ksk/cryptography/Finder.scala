package vc.ksk.cryptography
import scala.collection.mutable.ArrayBuffer

object Finder {

    def find(text : String, keyword : String) : List[(Int, Int)] = {
        import scala.collection.mutable.Set;
        //各文字の出現位置を記録
        var map = Map[Char, Set[Int]]();
        
        //initialize
        val key = keyword.toSet
        for (c <- key) {
            map += (c -> Set[Int]())
        }
        
        //find characters in given text 
        for (i <- 0 until text.length()) {
            val c = text.charAt(i);
            if (key.contains(c)) {
                //found
                map(c) += i;
            }
        }

        var result = List[(Int, Int)]();
        for (i <- map(keyword.charAt(0))) {
            for (j <- map(keyword.charAt(1))) {
                //calculate common difference 
                val d = j - i;
                var f = true;
                //2文字以降について初項・公差から計算される位置に文字が存在するか調査
                for (k <- 2 until keyword.length()) {
                    if (!map(keyword.charAt(k)).contains(i + (d * k))) {
                        f = false;
                    }
                }
                //すべての文字が存在した
                if (f) {
                    //とりあえずresultにいれていく
                    if (d > 0) {
                        result = (d, i + 1) +: result;
                    } else {
                        //公差負の時は逆順文字が存在したということ
                        result = (-d, i + (d * (keyword.length() - 1)) + 1) +: result;
                    }
                }
            }
        }

        //sort
        def s(a : (Int, Int), b : (Int, Int)) : Boolean = {
            if (a._1 == b._1) {
                a._2 < b._2;
            } else {
                a._1 < b._1;
            }
        }
        result.sortWith(s);
    }

    //fast version
    def fastFind(text : String, keyword : String) : List[(Int, Int)] = {
        import scala.collection.mutable.Set;
        //各文字の出現位置
        var map = Map[Char, Set[Int]]();
        //先頭文字の出現位置
        var first = ArrayBuffer[Int]();
        //末尾文字の出現位置
        var last = ArrayBuffer[Int]();
        
        //initialize
        val key = keyword.toSet
        for (c <- key) {
            map += (c -> Set[Int]())
        }

        //find characters in given text 
        for (i <- 0 until text.length()) {
            val c = text.charAt(i);
            if (key.contains(c)) {
                map(c) += i;
            }
            //for head character
            if (keyword.head == c) {
                first += i;
            }
            //for last character
            if (keyword.last == c) {
                last += i;
            }
        }

        //characters other than head and last
        val body = for (i <- 1 until keyword.length() - 1) yield { (i, keyword.charAt(i)) }

        //result
        var result = List[(Int, Int)]();
        for (i <- first) {
            for (j <- last if ((j - i) % (keyword.length() - 1) == 0)) {
                //common difference 
                val d = (j - i) / (keyword.length() - 1);
                //confirm all characters in body satisfy the restriction
                if (body.forall(x => map(x._2).contains(i + (x._1 * d)))) {
                    if (d > 0) {
                        result = (d, i + 1) +: result;
                    } else {
                        //reverse order of the keyword
                        result = (-d, i + (d * (keyword.length() - 1)) + 1) +: result;
                    }
                }
            }
        }

        //sort
        def s(a : (Int, Int), b : (Int, Int)) : Boolean = {
            if (a._1 == b._1) {
                a._2 < b._2;
            } else {
                a._1 < b._1;
            }
        }
        result.sortWith(s);
    }
}
