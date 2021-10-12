describe('Just testing', () => {
    it('just check cypress', () => {
        expect(true).to.equal(true)
    })
})

describe('Cy Visit mybook app', function() {
    it('test visit method', function() {
      cy.visit('http://localhost:3000')
    })
  });

  describe('testing login page',()=>{
    it('should enter emailid and password ',()=>{
        cy.get('input[id=email]').type('test@gmail.com');
        cy.get('input[id=password]').type('pass');
        cy.wait(4000);
        cy.get('.btn').click();
        cy.location().should( (location)=> expect(location.href).to.eq('http://localhost:3000/'));    
   
    })
})

  describe ('clicks on Sign Up', function() {
    it("clicks on Sign Up", function() {
      cy.visit('http://localhost:3000')
      cy.contains('Sign Up').click()
      cy.url().should('include', '/register')
    })
  })



  describe('testing register page',()=>{
    it('should enter details',()=>{
        cy.get('input[id=firstName]').type('testfirstname');
        cy.get('input[id=lastName]').type('testlastname');
        cy.get('input[id=email]').type('logintest@gmail.com');
        cy.get('input[id=password]').type('pass');
        cy.wait(4000);
        cy.get('.btn').click();
        cy.location().should( (location)=> expect(location.href).to.eq('http://localhost:3000/login'));    
   
    })
})

