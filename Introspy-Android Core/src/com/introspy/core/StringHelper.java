package com.introspy.core;

import android.util.Base64;

public class StringHelper {
	static public String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder();
		   for(byte b: a)
		      sb.append(String.format("%02x-", b&0xff));
		   sb.deleteCharAt(sb.length()-1);
		   return sb.toString();
		}

	static public String byteArrayToReadableStr(byte[] a) {
		   StringBuilder sb = new StringBuilder();
		   for(byte b: a) {
			   if (b >= 32 && b < 127)
				   sb.append(String.valueOf(b));
			   else
				   sb.append('.');
		   }
		   sb.deleteCharAt(sb.length()-1);
		   return sb.toString();
		}
	
	static public String byteArrayToB64(byte[] a) {
		return Base64.encodeToString(a, Base64.NO_WRAP);
	}
	
	// to check if a string only contains US-ASCII code point
	static public boolean isItReadable(String input) {
	    int readableChar = 0;
	    for (int i = 0; i < input.length(); i++) {
	        int c = input.charAt(i);
	        if ((c >= 32 && c < 127) || c == 9 || 
	        		c == 13 || c == 10 || c == 0) {
	        	readableChar++;
	        }
	    }
	    
	    // can be considered readable if X% characters are ascii
	    // (0 is considered a character here so that UTF16 
	    // can be considered readable too)
	    return (readableChar > (input.length() * 0.75) ? true : false);
	}
	
	 static public String escapeXMLChars(String s) {
		    return s.replaceAll("&",  "&amp;")
		         .replaceAll("'",  "&apos;")
		         .replaceAll("\"", "&quot;")
		         .replaceAll("<",  "&lt;")
		         .replaceAll(">",  "&gt;");
	 }
	
	static public String getReadableByteArr(byte[] input) {
		String out = new String(input);
		if (!StringHelper.isItReadable(out))
			out = StringHelper.byteArrayToB64(input);
		else
			out = byteArrayToReadableStr(input);
		return out;
	}
}