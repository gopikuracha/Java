package com.kriss.sample.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.kriss.sample.model.Person;
import com.kriss.util.Util;


public class TestSerialization {

	@Test
	public void testSerialization() {
		FileOutputStream fos = null;
		ObjectOutputStream os = null;
		try {
			Student owner = new Student(7, "Kriss");
			Person client = new Person("Gopi", "Krishna", "Kuracha", 32, null);
			SerializationSample ss = new SerializationSample(2, "Kriss", owner, client);
			fos = new FileOutputStream(new File("SerializationSample.ser"));
			os = new ObjectOutputStream(fos);
			os.writeObject(ss);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if(fos!=null) { try { fos.close(); } catch(IOException e) {e.printStackTrace();} }
			if(os!=null) { try { os.close(); } catch(IOException e) {e.printStackTrace();} }
		}
	}
	
	@Test
	public void testDeserialization() {
		FileInputStream fis = null;
		ObjectInputStream is = null;
		try {
			fis = new FileInputStream(new File("SerializationSample.ser"));
			is = new ObjectInputStream(fis);
			SerializationSample ss = (SerializationSample) is.readObject();
			Util.print(new Object[] {"Name", ss.getName(), "Id", ss.getId(), 
						"Owner.id", ss.getOwner().getId(), "Owner.name", ss.getOwner().getName(),
						"Client.age", ss.getClient().getAge(), "Client.name", ss.getClient().getFirstName(),
						"Parent.id", ss.getParentId(), "Parent.name", ss.getParentName()});
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} finally {
			if (fis!=null) { try { fis.close(); } catch(IOException e) { e.printStackTrace(); } }
			if (is!=null) { try { is.close(); } catch(IOException e) { e.printStackTrace(); } }
		}
	}
	
	@Test
	public void testSerializingNonSerializableParent() {
		FileOutputStream fos = null;
		ObjectOutputStream os = null;
		try {
			NonSerializableParentSample ns = new NonSerializableParentSample(2, "Krishna");
			fos = new FileOutputStream(new File("NonSerializableParentSample.ser"));
			os = new ObjectOutputStream(fos);
			os.writeObject(ns);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if(fos!=null) { try { fos.close(); } catch(IOException e) {e.printStackTrace();} }
			if(os!=null) { try { os.close(); } catch(IOException e) {e.printStackTrace();} }
		}
	}
	
	@Test
	public void testDeserializingNonSerializableParent() {
		FileInputStream fis = null;
		ObjectInputStream is = null;
		try {
			fis = new FileInputStream(new File("NonSerializableParentSample.ser"));
			is = new ObjectInputStream(fis);
			NonSerializableParentSample ns = (NonSerializableParentSample) is.readObject();
			Util.print(new Object[] {"Name", ns.getName(), "Id", ns.getId(),
						"Parent.id", ns.getParentId(), "Parent.name", ns.getParentName()});
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} finally {
			if (fis!=null) { try { fis.close(); } catch(IOException e) { e.printStackTrace(); } }
			if (is!=null) { try { is.close(); } catch(IOException e) { e.printStackTrace(); } }
		}
	}
}
