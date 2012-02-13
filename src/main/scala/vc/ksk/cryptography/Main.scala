package vc.ksk.cryptography
import vc.ksk.util.Reader

object Main {

    def main(args : Array[String]) : Unit = {
        if(args.length < 1){
            sys.exit();
        }

        if(args(0) == "find"){
            if(args.length < 2){
                sys.exit();
            }
            val result = Finder.find(Reader.readAll(Console.in),args(1));
            result.foreach{x=>
                println(x._1 + "," + x._2);
            }
        }
        
        if(args(0) == "fastFind"){
            if(args.length < 2){
                sys.exit();
            }
            val result = Finder.fastFind(Reader.readAll(Console.in),args(1));
            result.foreach{x=>
                println(x._1 + "," + x._2);
            }
        }
        
    }

}