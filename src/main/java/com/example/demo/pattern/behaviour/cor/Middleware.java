package com.example.demo.pattern.behaviour.cor;

public abstract class Middleware{
    private Middleware next;

    public Middleware getNext(){
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(String email, String password);

    /*
    Build chain of middleware objects
     */
    public static Middleware link(Middleware first, Middleware... chain){
        Middleware it = first;
        for (Middleware next : chain){
            it.next = next;
            it = next;
        }
        return first;
    }

    public Middleware add(Middleware md){
        Middleware it = this;
        while (it.next != null){
            it = it.next;
        }
        it.next = md;
        return this;
    }



    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(String email, String password) {
        if(next == null){
            // end of chain
            return true;
        }
        return next.check(email, password);
    }


}
