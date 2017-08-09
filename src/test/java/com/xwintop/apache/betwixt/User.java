package com.xwintop.apache.betwixt;

import java.io.Serializable;  
import java.util.Arrays;  
import java.util.HashMap;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Map;

import org.springframework.util.StringUtils;  
  
public class User implements Serializable {  
    private static final long serialVersionUID = 1354973253595584043L;  
    private String userName;  
    private int age;  
    private PersonBean person;  
    private List<String> hobbyList;  
    private Map<String, PersonBean> personMap;  
    private String[] hobbyArray;  
  
    /** 
     * 添加map类型属性的方法 
     * @param key 
     * @param person 
     * @date 2012-11-29下午1:01:06 
     */  
    public void addPersonMap(String key, PersonBean person) {  
        if (personMap == null) {  
            personMap = new HashMap<String, PersonBean>();  
        }  
        personMap.put(key, person);  
    }  
  
    /** 
     * 添加list类型的方法 
     * @param hobby 
     * @date 2012-11-29下午1:01:07 
     */  
    public void addHobbyList(String hobby) {  
        if (hobbyList == null) {  
            hobbyList = new LinkedList<String>();  
        }  
        hobbyList.add(hobby);  
    }  
  
    /** 
     * 添加数组类型的方法 
     * @param hobby 
     * @date 2012-11-29下午1:01:09 
     */  
    public void addHobbyArray(String hobby) {  
        hobbyArray = StringUtils.addStringToArray(hobbyArray, hobby);  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    public User() {  
        super();  
    }  
  
    public PersonBean getPerson() {  
        return person;  
    }  
  
    public void setPerson(PersonBean person) {  
        this.person = person;  
    }  
  
    public List<String> getHobbyList() {  
        return hobbyList;  
    }  
  
    public void setHobbyList(List<String> hobbyList) {  
        this.hobbyList = hobbyList;  
    }  
  
    public Map<String, PersonBean> getPersonMap() {  
        return personMap;  
    }  
  
    public void setPersonMap(Map<String, PersonBean> personMap) {  
        this.personMap = personMap;  
    }  
  
    public String[] getHobbyArray() {  
        return hobbyArray;  
    }  
  
    public void setHobbyArray(String[] hobbyArray) {  
        this.hobbyArray = hobbyArray;  
    }  
  
    @Override  
    public String toString() {  
        return "User [userName=" + userName + ", age=" + age + ", person=" + person + ", hobbyList=" + hobbyList  
                + ", personMap=" + personMap + ", hobbyArray=" + Arrays.toString(hobbyArray) + "]";  
    }  
  
}
