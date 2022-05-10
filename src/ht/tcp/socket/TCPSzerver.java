package ht.tcp.socket;

// TCPServer.java
import java.net.*;
import java.io.*;

public class TCPSzerver implements Runnable
{
	int port;

	public TCPSzerver(Socket s, PrintWriter pw)
	{
		try
		{
			port = s.getPort();
		}
		catch (Exception e)
		{
			System.out.println("port = 1500 (default)");
			port = 1500;
		}
	}

	@Override
	public void run()
	{

		System.out.println("Új szerver");
		ServerSocket server_socket;
		BufferedReader input;

		try
		{

			server_socket = new ServerSocket(port);
			System.out.println("Szerver varakozik a kliensre port:" +
					server_socket.getLocalPort());

			// szervert egy vegtelen ciklusban elinditjuk
			while(true) {
				Socket socket = server_socket.accept();
				System.out.println("Uj kapcsolat elfogadva " +
						socket.getInetAddress() +
						":" + socket.getPort());
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ki�rjuk a kapott �zenetet
				try {
					while(true)
					{
						String messageToBeSent;
						String message = input.readLine();
						if (message==null) break;

						double number = Double.parseDouble(message);

						if (((number / 4) % 1) == 0 && !(((number / 100) % 1) == 0))
						{
							messageToBeSent = "A kliens által küldött évszám szökőév";
						}
						else if (((number / 4) % 1) == 0 && ((number / 400) % 1) == 0)
						{
							messageToBeSent = "A kliens által küldött évszám szökőév";
						}
						else
						{
							messageToBeSent = "A kliens által küldött évszám NEM szökőév";
						}

						System.out.println(messageToBeSent);
					}
				}
				catch (IOException e) {
					System.out.println(e);
				}

				// a klienssel val� kapcsolatot lez�rjuk
				try {
					socket.close();
					System.out.println("Kapcsolat lezarasa");
				}
				catch (IOException e) {
					System.out.println(e);
				}

			}
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}

/*
public class TCPSzerver {
    public static void main(String args[]) {
	
	int port;
	ServerSocket server_socket;
	BufferedReader input;
	
	try { 
	    port = Integer.parseInt(args[0]);
	}
	catch (Exception e) {
	    System.out.println("port = 1500 (default)");
	    port = 1500;
	}

	try {
	    
	    server_socket = new ServerSocket(port);
	    System.out.println("Szerver varakozik a kliensre port:" + 
			       server_socket.getLocalPort());
	    
	    // szervert egy vegtelen ciklusban elinditjuk
	    while(true) {
		Socket socket = server_socket.accept();
		System.out.println("Uj kapcsolat elfogadva " +
				   socket.getInetAddress() +
				   ":" + socket.getPort());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		// ki�rjuk a kapott �zenetet
		try {
		    while(true) {
			String message = input.readLine();
			if (message==null) break;
			System.out.println(message);
		    }
		}
		catch (IOException e) {
		    System.out.println(e);
		}
		
		// a klienssel val� kapcsolatot lez�rjuk
		try {
		    socket.close();
		    System.out.println("Kapcsolat lezarasa");
		}
		catch (IOException e) {
		    System.out.println(e);
		}
		
	    }
	}
	catch (IOException e) {
	    System.out.println(e);
	}
    }
}*/
