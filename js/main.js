if (typeof web3 !== 'undefined') {
	  web3 = new Web3(web3.currentProvider);
	} else {
	  // set the provider you want from Web3.providers
   	 web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
	}
   	 web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));

	web3.eth.defaultAccount=web3.eth.accounts[0];
	 contract=web3.eth.contract([
	{
		"constant": false,
		"inputs": [
			{
				"name": "adds",
				"type": "address"
			},
			{
				"name": "_id",
				"type": "address"
			}
		],
		"name": "addBuyer",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "adds",
				"type": "address"
			},
			{
				"name": "_id",
				"type": "address"
			}
		],
		"name": "addCustomer",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_name",
				"type": "string"
			},
			{
				"name": "_mobile",
				"type": "uint256"
			},
			{
				"name": "_mail",
				"type": "string"
			}
		],
		"name": "setOwner",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_id",
				"type": "address"
			},
			{
				"name": "_productName",
				"type": "string"
			},
			{
				"name": "_company",
				"type": "string"
			},
			{
				"name": "_mobile",
				"type": "uint256"
			},
			{
				"name": "_mail",
				"type": "string"
			}
		],
		"name": "setProduct",
		"outputs": [],
		"payable": true,
		"stateMutability": "payable",
		"type": "function"
	},
	{
		"inputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "constructor"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "adds",
				"type": "address"
			}
		],
		"name": "getCustomerProducts",
		"outputs": [
			{
				"name": "",
				"type": "address[]"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "adds",
				"type": "address"
			}
		],
		"name": "getOwner",
		"outputs": [
			{
				"name": "",
				"type": "string"
			},
			{
				"name": "",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_id",
				"type": "address"
			}
		],
		"name": "getProductDetails",
		"outputs": [
			{
				"name": "",
				"type": "address"
			},
			{
				"name": "",
				"type": "string"
			},
			{
				"name": "",
				"type": "string"
			},
			{
				"name": "",
				"type": "address[]"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	}
]);
marketing=contract.at('0x5e07f8a748f20f72a9da747dde1d4a6cef6cec8c');
transactionObj={
	from:web3.eth.defaultAccount,
	gas:2000000,
	gasPrice:10000
};
