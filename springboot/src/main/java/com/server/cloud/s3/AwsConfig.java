package com.server.cloud.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

	
	@Value("${aws_access_key_id}")
	private String aws_access_key_id;
	
	@Value("${aws_secret_access_key}")
	private String aws_secret_access_key;
	

	@Bean
	public StaticCredentialsProvider staticCredentialsProvider() {
		AwsBasicCredentials credentials=AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
		StaticCredentialsProvider credentialsProvider=StaticCredentialsProvider.create(credentials);
		
		return credentialsProvider;
	}
	
	
	@Bean
	public S3Client s3client() {
		
		Region region = Region.AP_NORTHEAST_2;
		S3Client s3 = S3Client.builder()
				.region(region)
				.credentialsProvider(staticCredentialsProvider())
				.build();
		return s3;

	}
	


	@Bean
	public AwsBasicCredentials awsBasicCredentials() {
		
		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);

		return credentials;
	}

	
}
