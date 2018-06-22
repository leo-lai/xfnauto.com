/**
 * $Id: RefundStatus.java Dec 5, 2014 2:11:03 PM hdp
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
 * 退款状态
 * <ul>
 * <li>SUCCES—退款成功</li>
 * <li>FAIL—退款失败</li>
 * <li>PROCESSING—退款处理中</li>
 * <li>NOTSURE—未确定，需要商户原退款单号重新发起</li>
 * <li>ERROR—系统异常</li>
 * <li>CHANGE—转入代发，退款到
 * 银行发现用户的卡作废或者
 * 冻结了，导致原路退款银行
 * 卡失败，资金回流到商户的
 * 现金帐号，需要商户人工干
 * 预，通过线下或者财付通转
 * 账的方式进行退款。</li>
 * <p>创建时间: Dec 5, 2014 2:11:03 PM</p>
 * @author <a href="mailto:hongdanping@163.com">hdp</a>
 * @version V1.0
 * @since V1.0
 */
public enum RefundStatus {
	
	SUCCES("退款成功"), 
	FAIL("退款失败"), 
	PROCESSING("退款处理中"),
	NOTSURE("未确定"),
	CHANGE("转入代发"),
	ERROR("系统异常");
	
	private String value;
	
	private RefundStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
