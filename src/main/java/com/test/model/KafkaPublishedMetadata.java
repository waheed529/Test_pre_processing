package com.test.model;

public class KafkaPublishedMetadata {

	private String topicName;
	private long offset;

	private String key;

	private String statusMessage;

	private String dateTime;
	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	
	public KafkaPublishedMetadata(String topicName, String key, long offset, String statusMessage, String dateTime) {

		this.topicName = topicName;

		this.offset = offset;

		this.key = key;

		this.statusMessage = statusMessage;

		this.dateTime=dateTime;

	}



@Override

	public String toString() {

		return "KafkaPublishedMetaData [topicName=" + topicName + ", offset=" + offset + ", key=" + key

				+ ", statusMessage=" + statusMessage + "]";

	}
}
