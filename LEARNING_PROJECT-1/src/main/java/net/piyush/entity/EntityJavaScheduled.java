package net.piyush.entity;

import java.sql.Blob;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;


@Data

@Entity

/*
@Table(name="javascheduledj",schema="piyush_scheduled",
uniqueConstraints = { @UniqueConstraint(name = "UniqueNumberAndStatus", 
             columnNames = { "topicJ","chatpterJ"})
}   
) 
*/
@Table(name="javascheduledj",  uniqueConstraints={
		   @UniqueConstraint(name="U1",columnNames={"uniquej1j"}),
		   @UniqueConstraint(name="U2",columnNames={"uniquej2j"})
		})    //worked
public class EntityJavaScheduled {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJ;

    @Column(length = 90)
	private String overallJ;

	private String topicJ;
	
	private String chatpterJ;
	
	private String mode1j;
	
	//@Column(name="uniquej1j",unique = true)   //WORKED
	@Column(name="uniquej1j")
	private String uniqueIdJ1;  

	//@Column(name="uniquej2j",unique = true)   //WORKED
	@Column(name="uniquej2j") 
	private String uniqueIdJ2;
	
	private String mode2j;  
	
	private String clear1J; 
	
	private String clear2J;
	
	private String clear3J;

    @Column(name = "deleteflagj", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
	private char deleteFlagJ = 'N';
	
	@CreationTimestamp
	private LocalDateTime createdTimeJ;
	
	@UpdateTimestamp
	private LocalDateTime updateTimeJ;
	
	@Lob
	@Column(name="image_data",length = 100000)      //DATATYPE =mediumblob
	private byte[] imageDataJ;
	
	@Lob
	@Column(name = "blob_column",columnDefinition = "BLOB")    //DATATYPE =blob
	private Blob blobData;
	
	
}
