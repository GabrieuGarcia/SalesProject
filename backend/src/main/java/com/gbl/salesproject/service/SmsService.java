package com.gbl.salesproject.service;

import com.gbl.salesproject.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SaleService saleService;

    public void sendSms(Long id) {
        String newMessage = "";
        Twilio.init(twilioSid, twilioKey);

        Sale sale = this.findSaleById(id);
        if (sale != null) {
            newMessage = this.createMessage(sale);
        } else {
            newMessage = "No sales was found";
        }

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, newMessage).create();

        System.out.println(message.getSid());
    }

    private Sale findSaleById(Long saleId) {
        return this.saleService.findSaleById(saleId).orElse(null);
    }

    private String createMessage(Sale sale) {
        StringBuilder sb = new StringBuilder();
        sb.append("O(a) vendedor(a) ")
                .append(sale.getSellerName())
                .append(" vendeu ")
                .append(sale.getAmount().toString())
                .append("$")
                .append(" no dia ").append(sale.getDate().toString());

        return sb.toString();
    }
}
