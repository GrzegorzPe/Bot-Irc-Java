import java.io.IOException;
import java.net.Socket;
import java.lang.String;
import java.io.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;




     
public class Superprima {

//Dane do połaczenia sie bota  IRC

static String
        bot_owner = "phantomas-pht",
        nick_bot = "superprima",
        serv = "irc.pirc.pl",
        kanal = "#testbot";
//Koniec danych do połoczenia IRC


public static void main(String[] args) throws IOException {
	String kanal_temp = "#testbot";
        String str; 
        Socket s = new Socket(serv, 6667);
        BufferedReader i;
        PrintWriter o;
        i = new BufferedReader(new InputStreamReader(s.getInputStream()));
	o = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
 	        o.print(
 	               "USER " + nick_bot + " 0 * :" + bot_owner + "\r\n" +
 	               "NICK " + nick_bot + "\r\n");
	        o.flush();

//Zmienne trybów
 	
	//Tryb obecności
	String tryb_obecnosc="in";
	//Tryb cenzury
	String tryb_cenzura="on";
	//Ostatnia osoba ustawiajaca tryb
	String last_op="Superprima";
	//Ilość użytych wulgaryzmów
	int ilosc_wulgaryzm = 0;
	int przeklenstwo = 0;
	//Tryb Bota On/Off
	String tryb_bot = "on";
//Koniec zmiennych trybów


//Listy
	//Lista bot_op, zawiera operatorów bota, userów ktorzy mają dostęp do wszystkich jego opcji

	List<String> bot_op = new ArrayList<String>();
		bot_op.add("phantomas-pht");
		bot_op.add("piatkosia");
	//Koniec Listy bot_op
	//Lista wulgaryzm, jak sama nazwa wskazuje zawiera wulgaryzmy
	List<String> wulgaryzm = new ArrayList<String>();
                wulgaryzm.add("kurw");
                wulgaryzm.add("kurew");
                wulgaryzm.add("dupa");
                wulgaryzm.add("dziwka");
                wulgaryzm.add("pierdol");
                wulgaryzm.add("jeba");
		wulgaryzm.add("sukinsyn");
                wulgaryzm.add("rucha");
                wulgaryzm.add("dupy");
		wulgaryzm.add("huj");
		wulgaryzm.add("fuck");
		wulgaryzm.add("pierdalaj");
	//Koniec listy wulgaryzm
//Koniec Tablic


//Pętla while która funkcjonuje puki jest połaczenie.
	while (s.isConnected() && ((str = i.readLine()) != null))
	{               
//Splity i inne gówna
        String[] results = str.split(":");
	String cmd = results[results.length - 1];
//Tu machniemy stałe z poleceniami żeby sie nie pierdolić z klepaniem non stop
        String notice_nick = "NOTICE " + results[1].split("!")[0];
        String nick = results[1].split("!")[0];
        String priv_kanal = "PRIVMSG " + kanal_temp;
	String else_uprawnienia = notice_nick + " Przykro mi, nie masz odpowiednich uprawnień do wykonania tej operacji \r\n";
//Koniec  tego... 
		if (cmd.equals("!aktywacja") || str.contains("INVITE ")){
                	for (String spr_nick : bot_op){
                        	if ( nick.equals(spr_nick)){
					tryb_bot = "on";
                                	o.print ( "JOIN " + kanal + "\r\n");
                                }
                                else {
                                	o.print ( else_uprawnienia );
                }}}
                System.out.println(str);
               	if (str.startsWith("PING ")) {
                	o.print("PONG " + str.substring(5) + "\r\n");

                }
             	if ((str.split(" ")[1] != null) && str.split(" ")[1].equals("001")) {
	               	o.print(
				"MODE " + nick_bot + " +B\r\n" + 
        			"PRIVMSG nickserv identify kolos1 \r\n");
        	}
		if (cmd.equals("!rada")){
			o.print ( priv_kanal + " :Szanowani członkowie rady: piatkosia, nikow, lzsk, ar, teeed, gynvael, k4be. \r\n");
	               	o.print ( priv_kanal + " :Członkowie w stanie spoczynku (nieaktywni): Mad_Dud, komeniusz, wilq, mijagi. \r\n");
	        }
		if (cmd.equals("!rodzina")) {
		       	o.print ( notice_nick + " : Phantomas-pht - Tatuś, to on mnie skodził... Kocham go <3, Paulina - Mamusia <3. Piatkosia - Matka chrzestna. Nikow - Wujek, jest wredny, czasami. Dianull - Java-Mama, mówi do mnie dziwnym językiem...\r\n");
		}
	        if (cmd.equals("!stats")) {
		 	o.print ( priv_kanal + " : Statystyki kanału #listekklonu @ PIRC by k4be\r\n");
			o.print ( priv_kanal + " : http://shell.k4be.pl/listekklonu.html\r\n");
	        }
	        if (cmd.equals("!czas")){
			o.print ( priv_kanal + " : Czas: " + Calendar.getInstance().getTime() + " \r\n");
	        }
	        if (cmd.toLowerCase().equals("dobranoc")) {
			o.print("PRIVMSG " + kanal + " :Dobranoc\r\n");
	        }
	        if (cmd.equals("Wake Up Neo...")) {
			o.print ( priv_kanal + " : Follow The White Rabbit...\r\n");
			o.print ( priv_kanal + " : HS Warszawa: https://hackerspace.pl/\r\n");
			o.print ( priv_kanal + " : HS Kraków http://hackerspace-krk.pl/\r\n");
	        }
		if (cmd.equals("!kontakt")){
			o.print ( notice_nick + " : Możliwe opcje kontaktowe z phantomasem:\r\n");
			o.print ( notice_nick + " : E-Mail: phantomas@phtom.tk\r\n");
			o.print ( notice_nick + " : GG: 23205946\r\n");
			o.print ( notice_nick + " : Skype: pogromcazlych\r\n");
			o.print ( notice_nick + " : WWW: https://phtcom.tk\r\n");
			o.print ( notice_nick + " : Fcebook: https://www.facebook.com/PiotrPhanntomasJasiek\r\n");			o.print ( notice_nick + " : Twitter: @PhantomasPoland\r\n");
	        }
		if (cmd.equals("!identyfikacja")){
			o.print ( notice_nick + " Nazwa: Superprime.\r\n");
			o.print ( notice_nick + " Wersja: v 4.0(JAVA)\r\n");
			o.print ( notice_nick + " Nakodzony: 27.05.2013.\r\n");
			o.print ( notice_nick + " Autor Phantomas.\r\n");
			o.print ( notice_nick + " Kontakt: phantomas@phtcom.tk.\r\n");
		}
		if (cmd.equals("hello superprima") || cmd.equals("cz superprima") || cmd.equals("hi superprima")){
			o.print ( priv_kanal + " :Siemka, tu bot 'Superprima v4.0(JAVA)'. Mam ograniczony zasób odpowiedzi i reakcji. Aby uzyskać więcej info wpisz !help.\r\n");
		}
	        if (cmd.toLowerCase().equals(("bu"))) {
			o.print( priv_kanal + " : "+ nick +" Kotek :* (potwory i spółka)\r\n");
	        }
	        if (cmd.toLowerCase().equals(("cz"))) {
	               	o.print( priv_kanal + " : Hejcia "+ nick +" :*\r\n");
	        }
		if (cmd.equals("!help")){
			o.print ( notice_nick + " :Spoko, paczaj:\r\n");
			o.print ( notice_nick + " :Pomoc. \r\n");
			o.print ( notice_nick + " :!stats- Statystyki #listekklonu.\r\n");
			o.print ( notice_nick + " :!rodzina- Ludzie, którzy w jakikolwiek sposób przyczynili się do powstania bota.\r\n");
			o.print ( notice_nick + " :!identyfikacja- Dane programu takie jak wersja\r\n");
			o.print ( notice_nick + " :!kontakt- Lista kontaktowa autora bota użtkownika Phantomas\r\n");
			o.print ( notice_nick + " :Wake Up Neo...- Lista HS oraz ich www.\r\n");
               	        o.print ( notice_nick + " :!ping- Nudzisz się? Zagraj w Ping Ponga :).\r\n");
               	        o.print ( notice_nick + " :!bot operators - Operatorzy bota\r\n");
               	        o.print ( notice_nick + " :Opcje tylko dla operatorów bota.\r\n");
               	        o.print ( notice_nick + " :!cenzura on / !cenzura off - Wykrywanie wulgaryzmów w woadomościach.\r\n");
               	        o.print ( notice_nick + " :!tryb - Wyświetla aktualne ustawione tryby.\r\n");
			o.print ( notice_nick + " :!aktywacja - aktywuje bota i wprowadza bota na kanał, można użyć również opcji INVITE.\r\n");
				o.print ( notice_nick + " :!dezaktywacja - Dezaktywuje bota i wprowadza w stan uśpienia.");
		}
       	        if (cmd.equals("!ping")) {
			o.print( priv_kanal + " :" + nick +" pong :*\r\n");
		}
		if (cmd.toLowerCase().contains("win") && cmd.toLowerCase().contains("kobiet")){
			o.print ( priv_kanal + " :Kobieta Mnie Bijeeeeeeee!.\r\n");
		}
	//Cenzura
       	        if (cmd.equals("!cenzura on")){
       		        for (String spr_nick : bot_op){
               	                if (nick.equals(spr_nick)){
					ilosc_wulgaryzm = 0; 
					last_op=nick;
					tryb_cenzura= new String("on");
					o.print ( notice_nick + " :Ustawiłam tryb cenzury na: On\r\n");
				}
				else {
					o.print ( else_uprawnienia );
		}}}
       	        if (tryb_cenzura.equals("on"))
			for  (String slowo : wulgaryzm){
				if (cmd.toLowerCase().contains(slowo) && przeklenstwo == 0){
					ilosc_wulgaryzm++;
					przeklenstwo = 1;
					o.print ( priv_kanal +  " :" + nick + ": Proszę nie używać wulgaryzmów\r\n");
       	        }}
       	        if (cmd.equals("!cenzura off")){
			for (String spr_nick : bot_op){
				if (nick.equals(spr_nick)){ 
					last_op=nick;
					tryb_cenzura= new String("off");
               			        o.print ( notice_nick + " :Ustawiłam tryb cenzury na: Off Ilość użytych wulgaryzmów to:"  + ilosc_wulgaryzm + "\r\n");
				}
				else {
					o.print ( else_uprawnienia );
	        }}}
	//Koniec cenzury

	//Out/In home - powiadomienia na query
		if (cmd.equals("!outhome")){
			if (nick.equals("phantomas-pht")){
				tryb_obecnosc = new String("out");
				o.print ( notice_nick + " :Ustawiłam tryb obecności na: Out\r\n");
				}
			else {
				o.print ( else_uprawnienia );
		}}
		if (cmd.toLowerCase().contains("pht") && tryb_obecnosc.equals("out")){
			o.print ( "PRIVMSG phantomas-pht : Kto: " + nick +" Co: " + cmd + " \r\n");
		}
		if (cmd.equals("!inhome")){
			if (nick.equals("phantomas-pht")){
				tryb_obecnosc = new String("in");
					o.print ( notice_nick + " :Ustawiłam tryb obecnośći na: In \r\n");
			}
			else {
				o.print ( else_uprawnienia );
		}}
	//Koniec Out/In home - powiadomienia na query
	//Wypisanie trybów 
		if (cmd.equals("!tryb")){
       	        	for (String spr_nick : bot_op){
       	                	if ( nick.equals(spr_nick)){ 
					o.print ( notice_nick + " : Cenzura: " + tryb_cenzura + " Ustawiony przez: "+last_op+"\r\n");
					o.print ( notice_nick + " : Status Phantomasa na IRC: " +tryb_obecnosc+"\r\n");
						if (tryb_cenzura.equals("on")){
       							 o.print ( notice_nick + "Ilość użytych dotychczas wulgaryzmów to:" + ilosc_wulgaryzm + "\r\n");
				}}
				else {
					o.print ( else_uprawnienia );
		}}}
	//Koniec wypisania trybów
		if (cmd.equals("!bot operators")){
			o.print ( notice_nick + " :Operatorzy bota: " + bot_op.get(0) + " " + bot_op.get(1) +"\r\n");
       	        }
       	        if (cmd.equals("!dezaktywacja")){
       	                for (String spr_nick : bot_op){
       	                        if ( nick.equals(spr_nick)){ 
					o.print ( "PART " + kanal + " Bot został dezaktywowany\r\n");
				}
				else {
					o.print ( else_uprawnienia );
		}}}
	//Opy
		if (cmd.startsWith("!dajop")){
       	                for (String spr_nick : bot_op){
       	                        if ( nick.equals(spr_nick)){
	               			String[] gowno = cmd.split("!dajop ");
	               			String op = gowno[gowno.length - 1];
					o.print ( "MODE " + kanal_temp + " +o " + op.trim() + "\r\n");
		}}}
		if (cmd.startsWith("!odbierzop")){
       	                for (String spr_nick : bot_op){
       	                        if ( nick.equals(spr_nick)){
        	        		String[] gowno = cmd.split("!odbierzop ");
                			String op = gowno[gowno.length - 1];
					o.print ( "MODE " + kanal_temp + " -o " + op.trim() + "\r\n");
		}}}
	//Koniec opów
	//Bany
		if (cmd.startsWith("!banned")){
       	                for (String spr_nick : bot_op){
       	                        if ( nick.equals(spr_nick)){
	               			String[] gowno = cmd.split("!banned ");
	               			String op = gowno[gowno.length - 1];
					o.print ( "MODE " + kanal_temp + " +b " + op.trim() + "\r\n");
		}}}
		if (cmd.startsWith("!unbanned")){
       	                for (String spr_nick : bot_op){
       	                        if ( nick.equals(spr_nick)){
        	        		String[] gowno = cmd.split("!unbanned ");
                			String op = gowno[gowno.length - 1];
					o.print ( "MODE " + kanal_temp + " -b " + op.trim() + "\r\n");
		}}}
	//Koniec banów
	//"!kimjest"
		if (cmd.startsWith("!kimjest")){
   			String[] gowno = cmd.split("!kimjest ");
      			String op = gowno[gowno.length - 1];
			o.print ( "WHOIS " + op.trim() + "\r\n");
		}
		if (cmd.startsWith("!zmienkanal")){
       	                for (String huj : bot_op){
       	                        if ( nick.equals(huj)){
        	        		String[] zmiana = cmd.split("!zmienkanal ");
        	        		String new_kanal = zmiana[zmiana.length - 1];
					o.print ( "PART " + kanal_temp + " Opuszczam kanał. Polecenie wydał: " + nick + "\r\n");
					kanal_temp = new_kanal.trim();
					o.print ( "JOIN " + kanal_temp + "\r\n");
		}}}

               	o.flush();

	}
            o.close();
            i.close();
            s.close();
        }
	}

    

