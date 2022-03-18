package get_http_request.day15;

import org.junit.Test;

public class test {
    @Test
    public void test(){
        int time = 11;
        if (time<12) {//%50 soruda verilmis 11 diye bu kisim kapsiyor
            System.out.println("am");
        } else {//%50
            if (time > 12) {//%25 bu da soruda verilmis 15 diye bu kisim da kapsar
                System.out.println("pm");
            } else {//%25
                if (time == 12) {//%12.5 bu kisim verilmemis kapsamaz
                    System.out.println("noon");
                } else {//%12.5 bu kisim verilmemis ama zaten burda bir deger girilmesine
                        // gerek olmadigi icin burayi da kapsar sayabiliriz

                }
            }
        }
        //sponuc olarak kapsayanlari topladigimizda
        //50+25+12.5=87.5
    }
}
