pragma solidity ^0.4.24;
contract producting{
    struct product{
        address id;
        string productName;
        string company;
        address[] aowns;
        uint number;
        }
  

     address admin;
    
     struct owner{
        address  aown;
        string name;
        string mail;
        uint mobile;
     }
    
    constructor() public{
        admin=msg.sender;
        
    }
    
    
    struct customer{
        address[] products;
    }
    
    mapping (address=>customer) customers;
    
    mapping(address=>product) products;
    
    function setProduct(address _id,string _productName,string _company ,uint _mobile,string _mail) payable {
        products[_id].id=_id;
        products[_id].productName=_productName;
        products[_id].company=_company;
        products[_id].aowns.push(msg.sender);
        owners[msg.sender].aown=msg.sender;
        owners[msg.sender].name=_company;
    
        owners[msg.sender].mobile=_mobile;
        owners[msg.sender].mail=_mail;
        
        


        
    }
        mapping(address=>owner) owners;

     function setOwner(string _name,uint _mobile,string _mail) {
        owners[msg.sender].aown=msg.sender;
        owners[msg.sender].name=_name;
        owners[msg.sender].mobile=_mobile;
        owners[msg.sender].mail=_mail;
       
       

    }
    
    
    function addCustomer(address adds,address _id) {
        
    require(owners[adds].aown==adds);
     product p=products[_id];
        require(p.aowns[p.aowns.length-1]!=adds);
        
       
        require(p.aowns[p.aowns.length-1]==msg.sender);
        p.aowns.push(adds)-1;
        
        uint i=0;
        address[] array= customers[msg.sender].products;
        while(i<array.length){
            if(array[i]==_id)
                {
                    while(i<array.length-1){
                        array[i]=array[i+1];
                        i++;
                    }
                    
                    array.length--;
                    
                    break;
                }
            else
                i++;
        }
         customers[adds].products.push(_id);
        
    }
    
    
    function addBuyer(address adds,address _id) {
        
    require(owners[adds].aown==adds);
     product p=products[_id];
        require(p.aowns[p.aowns.length-1]!=adds);
        
       
        require(p.aowns[p.aowns.length-1]==msg.sender);
        p.aowns.push(adds)-1;
        
 
        
       
    }
    
    
    function getProductDetails(address _id) public constant returns(address,string,string,address[]){
        if(  products[_id].id!=_id ){
            throw;
        }
        product p=products[_id];


        return(p.id,p.productName,p.company,p.aowns);

    }
    
    function getOwner(address adds)  constant returns(string,string){
        require(owners[adds].aown==adds,"restricted");
        return(owners[adds].name,owners[adds].mail);
    }
    
    function getCustomerProducts(address adds) constant returns(address[]){

      return(customers[adds].products);
    }
    
    
    
    
    
    
    
    
    
    
   
    
        
}
