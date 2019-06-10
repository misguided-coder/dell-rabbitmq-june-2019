package com.example.ptp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo {

	public static void main(String[] args) throws Exception {
		
		String EXCAHNGE_NAME = "CarsEx";
		String ROUTING_KEY = "aboutcars";
				
		//Step 1 - Make a connection to RabbitMQ Broker
		ConnectionFactory factory = new ConnectionFactory();
		factory.setPort(8000);
		
		/*System.out.println(factory.getHost());
		System.out.println(factory.getPort());
		System.out.println(factory.getUsername());
		System.out.println(factory.getPassword());
		System.out.println(factory.getVirtualHost());*/
		
		Connection connection = factory.newConnection();
		System.out.println("Connected to Broker!!!!");
					
		//Step 2 - Create a channel using current connection (messaging session)
		Channel channel = connection.createChannel();
		
		//Step 3 - Prepare a message to send
		String message = "10 Jaguar Only";
		
		//Step 4 - Create a message publisher and Send the message to Broker
		//channel.basicPublish("", "CarsQ", false, null, message.getBytes());
		channel.basicPublish(EXCAHNGE_NAME, ROUTING_KEY, false, null, message.getBytes());
		
		System.out.println("Message sent to Broker!!!!");
		
		//Step 5 - Close and clean your connection and channel
		channel.close();
		connection.close();
		System.out.println("Disconnected from Broker!!!!");
	}
	
}
