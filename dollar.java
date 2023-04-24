class Dollar extends Money {
   private int amount;

   private String currency;

   Dollar(int amount, String currency) {
      super(amount, currency);
   }

   void times(int multiplier) {
      amount= amount * multiplier;
   }

   Money times(int multiplier)  {
      return Money.dollar(amount * multiplier);
   }

   public boolean equals(Object object) {
      Money money = (Money) object;
      return amount == money.amount && getClass().equals(money.getClass());
   }

   String currency() {
       return "USD";
    }
}

public void testMultiplication() {
   Money five = Money.dollar(5);
   assertEquals(Money.dollar(10), five.times(2));
   assertEquals(Money.dollar(15), five.times(3));
}

public void testEquality() {
   assertTrue(new Dollar(5).equals(Money.dollar(5)));
   assertFalse(new Dollar(5).equals(Money.dollar(6)));
   assertTrue(new Franc(5).equals(Money.franc(5)));
   assertFalse(new Franc(5).equals(Money.franc(6)));
   assertFalse(new Franc(5).equals(Money.dollar(5)));
}

public boolean equals(Object object)  {
   Dollar dollar = (Dollar) object;
   return amount == dollar.amount;
}

public void testFrancMultiplication() {
   Money five = Money.franc(5);
   assertEquals(Money.franc(10), five.times(2));
   assertEquals(Money.franc(15), five.times(3));
}

class Franc extends Money {   
   private int amount;

   private String currency;

   Franc(int amount, String currency) {      
      super(amount, currency);
    }

    Money times(int multiplier)  {
      return Money.franc(amount * multiplier);
    }

    public boolean equals(Object object) {					
       Franc franc = (Franc) object;      
       return amount == franc.amount;					
    }

    String currency() {
      return "CHF";
   }					
}

public void testCurrency() {
   assertEquals("USD", Money.dollar(1).currency());
   assertEquals("CHF", Money.franc(1).currency());
}

abstract class Money  {
   protected int amount;

   private String currency;
   
   public boolean equals(Object object)  {
      Money money = (Money) object;
      return amount == money.amount;
   }

   static Money dollar(int amount) {
    return new Dollar(amount, "USD");
   }

   static Money franc(int amount) {
      return new Franc(amount, "CHF");
    }

    Money(int amount, String currency) {
      this.amount = amount;
      this.currency = currency;
    }

   abstract Money times(int multiplier);
   protected String currency();

   String currency() {
      return currency;
   }
}

public void testDifferentClassEquality() {
   assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
}