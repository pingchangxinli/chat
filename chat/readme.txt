1.用httpClient发起https请求，网页crt，window要通过keytool转换成keystore；
2.javax.net.ssl.SSLProtocolException: handshake alert: unrecognized_name 错误用下面解决
	-Djsse.enableSNIExtension=false 或者 System.setProperty ("jsse.enableSNIExtension", "false");
3.	
	
	