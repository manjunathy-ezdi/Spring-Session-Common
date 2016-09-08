package com.ezdi.redisserializer.springsecurity;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class EzDummyHttpServletRequest implements HttpServletRequest{
	
	private Map<String,String> headerMap;
	private Map<String,String[]> parameterMap;
	private String characterEncoding;
	private long contentLengthLong;
	private int contentLength;
	private String contentType;
	private ServletInputStream inputStream;
	private String protocol;
	private String scheme;
	private String serverName;
	private int serverPort;
	private BufferedReader reader;
	private String remoteAddr;
	private String remoteHost;
	private Locale locale;
	private List<Locale> locales;
	private boolean secure;
	private RequestDispatcher requestDispatcher;
	private String realPath;
	private int remotePort;
	private String localName;
	private String localAddr;
	private int localPort;
	private ServletContext servletContext;
	private boolean asyncStared;
	private boolean asyncSupported;
	private AsyncContext asyncContext;
	private DispatcherType dispatcherType;
	private String authType;
	private Cookie[] cookies;
	private long dateHeader;
	private String method;
	private String pathInfo;
	private String pathTranslated;
	private String contextPath;
	private String queryString;
	private String remoteUser;
	private boolean userInRole;
	private Principal userPrincipal;
	private String requestedSessionId;
	private String requestURI;
	private StringBuffer requestURL;
	private String servletPath;
	private HttpSession session;
	private boolean requestedSessionIdValid;
	private boolean requestedSessionIdFromCookie;
	private boolean requestedSessionIdFromURL;
	private boolean requestedSessionIdFromUrl;
	private Collection<Part> parts;
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}
	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
	public long getContentLengthLong() {
		return contentLengthLong;
	}
	public void setContentLengthLong(long contentLengthLong) {
		this.contentLengthLong = contentLengthLong;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public ServletInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ServletInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public BufferedReader getReader() {
		return reader;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Enumeration<Locale> getLocales() {
		return (Enumeration<Locale>)locales;
	}
	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}
	public boolean isSecure() {
		return secure;
	}
	public void setSecure(boolean secure) {
		this.secure = secure;
	}
	public RequestDispatcher getRequestDispatcher() {
		return requestDispatcher;
	}
	public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public int getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getLocalAddr() {
		return localAddr;
	}
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public boolean isAsyncStared() {
		return asyncStared;
	}
	public void setAsyncStared(boolean asyncStared) {
		this.asyncStared = asyncStared;
	}
	public boolean isAsyncSupported() {
		return asyncSupported;
	}
	public void setAsyncSupported(boolean asyncSupported) {
		this.asyncSupported = asyncSupported;
	}
	public AsyncContext getAsyncContext() {
		return asyncContext;
	}
	public void setAsyncContext(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}
	public DispatcherType getDispatcherType() {
		return dispatcherType;
	}
	public void setDispatcherType(DispatcherType dispatcherType) {
		this.dispatcherType = dispatcherType;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public Cookie[] getCookies() {
		return cookies;
	}
	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}
	public long getDateHeader() {
		return dateHeader;
	}
	public void setDateHeader(long dateHeader) {
		this.dateHeader = dateHeader;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPathInfo() {
		return pathInfo;
	}
	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}
	public String getPathTranslated() {
		return pathTranslated;
	}
	public void setPathTranslated(String pathTranslated) {
		this.pathTranslated = pathTranslated;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getRemoteUser() {
		return remoteUser;
	}
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	public boolean isUserInRole() {
		return userInRole;
	}
	public void setUserInRole(boolean userInRole) {
		this.userInRole = userInRole;
	}
	public Principal getUserPrincipal() {
		return userPrincipal;
	}
	public void setUserPrincipal(Principal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
	public String getRequestedSessionId() {
		return requestedSessionId;
	}
	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public StringBuffer getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(StringBuffer requestURL) {
		this.requestURL = requestURL;
	}
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public boolean isRequestedSessionIdValid() {
		return requestedSessionIdValid;
	}
	public void setRequestedSessionIdValid(boolean requestedSessionIdValid) {
		this.requestedSessionIdValid = requestedSessionIdValid;
	}
	public boolean isRequestedSessionIdFromCookie() {
		return requestedSessionIdFromCookie;
	}
	public void setRequestedSessionIdFromCookie(boolean requestedSessionIdFromCookie) {
		this.requestedSessionIdFromCookie = requestedSessionIdFromCookie;
	}
	public boolean isRequestedSessionIdFromURL() {
		return requestedSessionIdFromURL;
	}
	public void setRequestedSessionIdFromURL(boolean requestedSessionIdFromURL) {
		this.requestedSessionIdFromURL = requestedSessionIdFromURL;
	}
	public boolean isRequestedSessionIdFromUrl() {
		return requestedSessionIdFromUrl;
	}
	public void setRequestedSessionIdFromUrl(boolean requestedSessionIdFromUrl) {
		this.requestedSessionIdFromUrl = requestedSessionIdFromUrl;
	}
	public Collection<Part> getParts() {
		return parts;
	}
	public void setParts(Collection<Part> parts) {
		this.parts = parts;
	}
	@Override
	public Object getAttribute(String name) {
		return null;
	}
	@Override
	public Enumeration<String> getAttributeNames() {
		return null;
	}
	@Override
	public String getParameter(String name) {
		return null;
	}
	@Override
	public Enumeration<String> getParameterNames() {
		return null;
	}
	@Override
	public String[] getParameterValues(String name) {
		return null;
	}
	@Override
	public void setAttribute(String name, Object o) {
		
	}
	@Override
	public void removeAttribute(String name) {
		
	}
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return null;
	}
	@Override
	public String getRealPath(String path) {
		return null;
	}
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		return null;
	}
	@Override
	public boolean isAsyncStarted() {
		return false;
	}
	@Override
	public long getDateHeader(String name) {
		return 0;
	}
	@Override
	public String getHeader(String name) {
		return null;
	}
	@Override
	public Enumeration<String> getHeaders(String name) {
		return null;
	}
	@Override
	public Enumeration<String> getHeaderNames() {
		return null;
	}
	@Override
	public int getIntHeader(String name) {
		return 0;
	}
	@Override
	public boolean isUserInRole(String role) {
		return false;
	}
	@Override
	public HttpSession getSession(boolean create) {
		return null;
	}
	@Override
	public String changeSessionId() {
		return null;
	}
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return false;
	}
	@Override
	public void login(String username, String password) throws ServletException {
		
	}
	@Override
	public void logout() throws ServletException {
		
	}
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return null;
	}
	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
			throws IOException, ServletException {
		return null;
	}	
}