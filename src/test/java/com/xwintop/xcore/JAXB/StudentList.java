package com.xwintop.xcore.JAXB;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "list")
public class StudentList {

	List<Student> students; // 所有学生信息的集合

	Data data;
	
	@XmlElement(name = "student")
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@XmlElement(name = "data")
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}
