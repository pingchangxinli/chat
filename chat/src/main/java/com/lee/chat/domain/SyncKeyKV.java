package com.lee.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SyncKeyKV {
	@JsonProperty(value="Key")
	private Integer key;
	@JsonProperty(value="Val")
	private Integer value;
}
