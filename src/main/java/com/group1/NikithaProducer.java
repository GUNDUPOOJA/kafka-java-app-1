package com.group1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by sunilpatil on 12/28/15.
 */
public class NikithaProducer {
    private static Scanner in;

    public static void main(String[] argv) throws Exception {
      if (argv.length != 1) {
        System.err.println("Please specify 1 parameter (the name of the topic)");
        System.exit(-1);
      }
      String topicName = argv[0];
      in = new Scanner(System.in);
      System.out.println("Thank you for providing the topic " + topicName + "\n");
      System.out.println("Enter message (type exit to quit).\n");
  
      // Configure the Producer
      Properties configProperties = new Properties();
      configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
      configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.ByteArraySerializer");
      configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.StringSerializer");
      System.out.println("The configuration properties are: " + configProperties.toString());
      System.out.println("\nWill use this configuration to create a producer.\n");
  
      org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
  
      // Make our own messages - create your custom logic here
  
      
        String message = createTips();
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, message);
        producer.send(rec);
     
  
      in.close();
      producer.close();
    }

    private static String createTips() {
        return "Tips for creating attractive GitHub Profile:\n" 
                + "Update the code constantly"
                + "Write clean committed and well-organized code"
                + "Work as a Team"
                + " Contribution";
      }

}