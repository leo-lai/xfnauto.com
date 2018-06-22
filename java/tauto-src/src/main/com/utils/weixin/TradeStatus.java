/**
 * $Id: TradeStatus.java Nov 20, 2014 5:55:56 PM hdp
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.com.utils.weixin;

/**
 * 交易状态
 * <p>创建时间: Nov 20, 2014 5:55:56 PM</p>
 * @author <a href="mailto:hongdanping@163.com">hdp</a>
 * @version V1.0
 * @since V1.0
 */
public enum TradeStatus {
	/**
	 * 预支付状态
	 */
	PRAPARE("预支付状态"),
	/**
	 * 支付成功
	 */
	SUCCESS("支付成功"),
	/**
	 * 支付失败
	 */
	FAIL("支付失败"),
	/**
	 * 已退款
	 */
	REFUND("已退款")
	;
	
	private String value;
	
	private TradeStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
