package day1;

public class CodingBatLogic1 {
    public boolean cigarParty(int cigars, boolean isWeekend) {
        return (cigars>=40 && ((!isWeekend && cigars<=60) || isWeekend));
    }

    public int dateFashion(int you, int date) {
        return (you<=2 || date<=2) ? 0 : (you>=8 || date>=8) ? 2 : 1;
    }

    public boolean squirrelPlay(int temp, boolean isSummer) {
        return temp>=60 && ((!isSummer && temp<=90) || (isSummer && temp<=100));
    }
      
    public int caughtSpeeding(int speed, boolean isBirthday) {
        int bDayBonus = isBirthday ? 5 : 0;
        return speed<=60 + bDayBonus ? 0 : speed>=61 && speed<=80 + bDayBonus ? 1 : speed>=81 + bDayBonus ? 2 : 0;
    }
      
    public int sortaSum(int a, int b) {
        return (a+b)>=10 && (a+b)<=19 ? 20 : a + b;
    }
    
    public String alarmClock(int day, boolean vacation) {
        return day>=1 && day<=5 && !vacation ? "7:00" : day>=1 && day<=5 ? "10:00" : (day==0 || day==6) && vacation ? "off" : "10:00";
    }

    public boolean love6(int a, int b) {
        return a==6 || b==6 || a+b==6 || Math.abs(a-b)==6;
    }
      
    public boolean in1To10(int n, boolean outsideMode) {
        return (outsideMode && (n<=1 || n>=10)) || (!outsideMode && n>=1 && n<=10);
    }

    public boolean specialEleven(int n) {
        return n % 11 == 0 || (n-1) % 11 == 0;
    }

    public boolean more20(int n) {
        return (n-1)%20==0 || (n-2)%20==0;
    }
}
