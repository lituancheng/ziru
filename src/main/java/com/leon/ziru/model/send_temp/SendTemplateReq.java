package com.leon.ziru.model.send_temp;

/**
 * Created by lituancheng on 2018/10/10
 */
public class SendTemplateReq {

    public String touser;
    public String template_id;
    public String page;
    public String form_id;
    public Data data;

    public static class Data{

        public Keyword keyword1;
        public Keyword keyword2;
        public Keyword keyword3;
        public Keyword keyword4;

        public static class Keyword{
            public String value;

            public Keyword(String value){
                this.value = value;
            }
        }
    }
}
