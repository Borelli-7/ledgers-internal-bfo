/*
 * Copyright 2018-2018 adorsys GmbH & Co KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.adorsys.ledgers.um.api.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AisAccountAccessInfoBO {

    private List<String> accounts;

    private List<String> balances;

    private List<String> transactions;

    private AisAccountAccessTypeBO availableAccounts;

    private AisAccountAccessTypeBO allPsd2;

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	public List<String> getBalances() {
		return balances;
	}

	public void setBalances(List<String> balances) {
		this.balances = balances;
	}

	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}

	public AisAccountAccessTypeBO getAvailableAccounts() {
		return availableAccounts;
	}

	public void setAvailableAccounts(AisAccountAccessTypeBO availableAccounts) {
		this.availableAccounts = availableAccounts;
	}

	public AisAccountAccessTypeBO getAllPsd2() {
		return allPsd2;
	}

	public void setAllPsd2(AisAccountAccessTypeBO allPsd2) {
		this.allPsd2 = allPsd2;
	}
    
    
}
