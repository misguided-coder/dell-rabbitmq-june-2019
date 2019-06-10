package com.example.ptp;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiverDemo {

	public static void main(String[] args) throws Exception {

		// Step 1 - Make a connection to RabbitMQ Broker
		ConnectionFactory factory = new ConnectionFactory();
		factory.setPort(8000);

		/*
		 * System.out.println(factory.getHost()); System.out.println(factory.getPort());
		 * System.out.println(factory.getUsername());
		 * System.out.println(factory.getPassword());
		 * System.out.println(factory.getVirtualHost());
		 */

		Connection connection = factory.newConnection();
		System.out.println("Connected to Broker!!!!");

		// Step 2 - Create a channel using current connection (messaging session)
		Channel channel = connection.createChannel();

		// Step 3 - Create a message consumer
		Consumer consumer = new DefaultConsumer(channel) {

			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				System.out.printf("Received : %s%n",new String(body));
			}
		};

		// Step 4 - start consumption and receive the message from Broker
		channel.basicConsume("CarsQ", true, consumer);
	
		//Thread.sleep(1000*10);
		
		// Step 5 - Close and clean your connection and channel
		//channel.close();
		//connection.close();
		//System.out.println("Disconnected from Broker!!!!");
	}

}
