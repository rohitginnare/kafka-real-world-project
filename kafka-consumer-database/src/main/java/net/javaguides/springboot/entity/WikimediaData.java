package net.javaguides.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikimedia_recentchange")
@Getter
@Setter
public class WikimediaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	In Spring Boot, @Lob is an annotation used to specify that a field in an entity class should be 
//	mapped to a large object (LOB) column in the database. 
//	LOB columns are used to store large amounts of data, such as text, binary data, or serialized objects. 
//	The @Lob annotation is typically used with fields of types like String, byte[], or java.sql.Blob.
	
	@Lob   //Large Object 
	private String wikiEventData;
	
	
}
