package com.patrick.languagessql.models;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Language {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min = 2, max = 20)
	private String name;
	
	@Column
	@Size(min = 2, max = 20)
	private String creator;

	@Column(nullable = false)
	private String version;
	
    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date created_at;

    @Column
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updated_at;
    
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
    
    public Language() {
	}
	
	public Language(String name, String creator, String version, Date created_at, Date updated_at) {
		this.name = name;
		this.creator = creator;
		this.version = version;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreated() {
		return created_at;
	}
	
	public void setCreated(Date created_at) {
		this.created_at = created_at;
	}
	
	public Date getUpdated() {
		return updated_at;
	}
	
	public void setUpdated(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getName() {
        return name;
	}
	public void setName(String name) {
	        this.name = name;
	}
	public String getCreator() {
	        return creator;
	}
	public void setCreator(String creator) {
	        this.creator = creator;
	}
	public String getVersion() {
	        return version;
	}
	public void setVersion(String version) {
	        this.version = version;
	}
}
