package cn.chengzi.entity;

public class Person {

	private String id;
	private String name;
	private String age;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Person() {
		super();
	}
	public Person(String id, String name, String age, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.remark = remark;
	}
}
