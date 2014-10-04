package fr.esiag.archicloud.push.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class GPSChannelServlet extends HttpServlet {


	private static final long serialVersionUID = 708082624874334976L;
	
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {    
	    String channelKey = req.getParameter("c");

	    //Create a Channel using the 'channelKey' we received from the client
	    ChannelService channelService = ChannelServiceFactory.getChannelService();
	    String token = channelService.createChannel(channelKey);

	    //Send the client the 'token' + the 'channelKey' this way the client can start using the new channel
	    resp.setContentType("text/html");
	    StringBuffer sb = new StringBuffer();
	    sb.append("{ \"channelKey\":\"" + channelKey + "\",\"token\":\"" + token + "\"}");

	    resp.getWriter().write(sb.toString());
	  }
	

}
