/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package tcp.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;


/**
 * @author Joan-Manuel Marques
 * @author Christian Gutiérrez Antolín
 */

public class HTTPclient {

	public HTTPclient() {
	}
			
	public String get(String http_server_address, int http_server_port){
		LSimLogger.log(Level.INFO, "inici HTTPclient.get ");
		LSimLogger.log(Level.INFO, "HTTP server_address: " + http_server_address);
		LSimLogger.log(Level.INFO, "HTTP server_port: " + http_server_port);

		String peticion = "OPTIONS / HTTP/1.0\r\n"; /* TODO: Petició HTTP / Petición HTTP */
		
	   	LSimLogger.log(Level.INFO, peticion);

	   	String result = "";
	
		/* TODO: implementació de la part client TCP / implementación de la parte cliente TCP */
	   	Socket socket;
	   		
	   	try {
	   		// Declare variables I need
	   		PrintWriter printWriter;
	   		InputStreamReader inputStreamReader; 
	   		BufferedReader bufferedReader;
	   		String resultServerLine;
	   		
	   		
	   		// Create socket for connection with the parameters
			socket = new Socket(http_server_address, http_server_port);
			LSimLogger.log(Level.TRACE, "Socket created");

			
	   		// Create the PrintWriter with the OutputStream of the socket and print the request
			printWriter = new PrintWriter(socket.getOutputStream(), true);
	   		printWriter.println(peticion);
	   		
	   		// Create the Input Stream
	   		inputStreamReader  = new InputStreamReader(socket.getInputStream());
	   		bufferedReader = new BufferedReader(inputStreamReader);
	   		
	   		// Creating the final text to be returned
	   		resultServerLine = bufferedReader.readLine();
	   		
	   		LSimLogger.log(Level.TRACE, "Creating result");
	   		
	   		while (resultServerLine!= null && !resultServerLine.equals("")) {
	   			result += resultServerLine + "\r\n";
		   		resultServerLine = bufferedReader.readLine();
	   		}
	   		
	   		LSimLogger.log(Level.TRACE, "Result created");
	   		
	   		// Close socket
	   		socket.close();
	   		LSimLogger.log(Level.TRACE, "Socket closed");
	   	} catch (IOException e) {
			LSimLogger.log(Level.ERROR, "Error HTTPclient: " + e.getMessage());
		}	   			
	   	
	   	return result;
	}
}