package com.lz.study.https;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
		try {
			if (!(connection instanceof HttpsURLConnection)) {
				throw new RuntimeException("An instance of HttpsURLConnection is expected");
			}

			HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

			} };
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			httpsConnection.setSSLSocketFactory(new XlCustomSSLSocketFactory(sslContext.getSocketFactory()));

			httpsConnection.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});

			super.prepareConnection(httpsConnection, httpMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  SSLSocketFactory用于创建 SSLSockets
	 */
	private static class XlCustomSSLSocketFactory extends SSLSocketFactory {

		private final SSLSocketFactory delegate;

		public XlCustomSSLSocketFactory(SSLSocketFactory delegate) {
			this.delegate = delegate;
		}

		// 返回默认启用的密码套件。除非一个列表启用，对SSL连接的握手会使用这些密码套件。
		// 这些默认的服务的最低质量要求保密保护和服务器身份验证
		@Override
		public String[] getDefaultCipherSuites() {
			return delegate.getDefaultCipherSuites();
		}

		// 返回的密码套件可用于SSL连接启用的名字
		@Override
		public String[] getSupportedCipherSuites() {
			return delegate.getSupportedCipherSuites();
		}

		@Override
		public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose)
				throws IOException {
			final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
			return overrideProtocol(underlyingSocket);
		}

		@Override
		public Socket createSocket(final String host, final int port) throws IOException {
			final Socket underlyingSocket = delegate.createSocket(host, port);
			return overrideProtocol(underlyingSocket);
		}

		@Override
		public Socket createSocket(final String host, final int port, final InetAddress localAddress,
				final int localPort) throws IOException {
			final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
			return overrideProtocol(underlyingSocket);
		}

		@Override
		public Socket createSocket(final InetAddress host, final int port) throws IOException {
			final Socket underlyingSocket = delegate.createSocket(host, port);
			return overrideProtocol(underlyingSocket);
		}

		@Override
		public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress,
				final int localPort) throws IOException {
			final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
			return overrideProtocol(underlyingSocket);
		}

		private Socket overrideProtocol(final Socket socket) {
			if (!(socket instanceof SSLSocket)) {
				throw new RuntimeException("An instance of SSLSocket is expected");
			}
			((SSLSocket) socket).setEnabledProtocols(new String[] { "TLSv1" });
			return socket;
		}
	}
	
}
