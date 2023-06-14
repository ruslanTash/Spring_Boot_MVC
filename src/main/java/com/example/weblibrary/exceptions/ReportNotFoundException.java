package com.example.weblibrary.exceptions;

public class ReportNotFoundException extends RuntimeException{
        private final int id;
        public ReportNotFoundException(int id){
            this.id = id;
        }

        public int getId() {
            return id;
        }
}

