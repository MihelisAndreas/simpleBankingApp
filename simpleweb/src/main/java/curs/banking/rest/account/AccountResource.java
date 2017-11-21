package curs.banking.rest.account;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import curs.banking.business.AccountService;
import curs.banking.db.utils.DataSourceConnectionFactory;
import curs.banking.model.Account;
import curs.banking.model.AccountType;
import curs.banking.model.Currency;
import curs.banking.rest.AccountResourceIntf;

public class AccountResource implements AccountResourceIntf {
	@Context HttpServletRequest mRequest;
	@Context HttpServletResponse mResponse;

	@Override
	public Collection<Account> getAccounts() throws Exception {
		mResponse.setHeader("pufi", "fifi");
		if(mRequest.getUserPrincipal() == null) {
			return new ArrayList<Account>();
		}
		return new AccountService(DataSourceConnectionFactory.factory()).loadAllAccounts();
	}

	@Override
	public Account getAccount(long pId) throws Exception {
		return new AccountService(DataSourceConnectionFactory.factory()).loadAccountById(pId);

	}

	@Override
	public Account addAccount(String pIBAN, double pSold, long pClientId, AccountType pType, Currency pCurrency) throws Exception {
		AccountService as = new AccountService(DataSourceConnectionFactory.factory());
		return as.createAccount(pIBAN, pSold, pClientId, pType, pCurrency);
	}

	
	@Override
	public Account updateAccount(long pId, Account pAccount) throws Exception {
		AccountService as = new AccountService(DataSourceConnectionFactory.factory());
		return as.updateAccount(pId, pAccount);
	}
	
	// GET ACCOUNT BY ID
}
