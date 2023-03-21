package com.lanchoneteSystem.sistema;

import java.time.LocalDate;
import java.util.Calendar;

public class Sistema {
    private static boolean isAdm;
    private static String userLogged;

    
    /**
     * Filtra a data em um intervalo de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param horaInicial horário inicial
     * @param horaFinal horário final
     * @param compareDate define a data que será comparada, ou seja, a passada pelo usuário
     * @param compareHour define a hora que será comparada, ou seja, a passada pelo usuário
     * @return retorna se a data passada está no intervalo(true) ou não(false)
     */
    public boolean filterDate(String date1, String date2, int horaInicial, int horaFinal, String compareDate, int compareHour){
        LocalDate t1 = LocalDate.parse(date1);
        LocalDate t2 = LocalDate.parse(date2);
        LocalDate compareTime = LocalDate.parse(compareDate);
        
        Calendar dataInicial = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();
        Calendar compareData = Calendar.getInstance();
        
        dataInicial.set(t1.getYear(), t1.getMonthValue(), t1.getDayOfMonth(), horaInicial, 0);
        dataFinal.set(t2.getYear(), t2.getMonthValue(), t2.getDayOfMonth(), horaFinal, 0);
        compareData.set(compareTime.getYear(), compareTime.getMonthValue(), compareTime.getDayOfMonth(), compareHour, 0);
     
        return compareData.getTimeInMillis() >= dataInicial.getTimeInMillis() && compareData.getTimeInMillis() <= dataFinal.getTimeInMillis();
    }
    
    
    /**
     * 
     * @return retorna se é ou não ADM
     */
    public static boolean getIsAdm() {
        return isAdm;
    }
    
    /**
     * 
     * @param isAdm define se é ou não ADM
     */
    public static void setIsAdm(boolean isAdm) {
        Sistema.isAdm = isAdm;
    }
    
    /**
     * 
     * @return retorna o CPF do usuário que logou
     */
    public static String getUserLogged() {
        return userLogged;
    }
    
    /**
     * 
     * @param userLogged define o CPF de quem logou
     */
    public static void setUserLogged(String userLogged) {
        Sistema.userLogged = userLogged;
    }

    @Override
    public String toString() {
        return "Sistema{" + '}';
    }
  
}
