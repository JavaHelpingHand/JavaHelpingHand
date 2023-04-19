package com.example.demo;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

@Service
public class SerialCommunicationService {

	public List<String> getPorts(){
	return Arrays.stream(SerialPort.getCommPorts())
			.map(SerialPort::getSystemPortName).collect(Collectors.toList());
	}
	
	public String communicate() {
		
		SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort serialPort = ports[3]; // Change this to the appropriate port for your system
        
        // Configure the serial port
        serialPort.openPort();
        serialPort.setComPortParameters(9600, 8, 1, 0);
        
        // Get the output stream from the serial port
        OutputStream outputStream = serialPort.getOutputStream();
		
		byte [] data = {2, 7,9,4, 10,25,20};
		serialPort.addDataListener(
				new SerialPortDataListener() {
					
					@Override
					public void serialEvent(SerialPortEvent event) {
						if(event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
					byte[] recievedData = new byte[serialPort.bytesAvailable()];
					serialPort.readBytes(recievedData, recievedData.length);
					}
					
					@Override
					public int getListeningEvents() {
						return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
					}
				});
		serialPort.writeBytes(data, data.length);
		//serialPort.closePort();
		//serialPort.removeDataListener();
	
	return "Communication Successfull";
}}
