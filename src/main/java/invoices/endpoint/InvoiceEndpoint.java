package invoices.endpoint;

import invoices.objects.Invoice;
import invoices.repositories.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xyz.streetshirt.soap.*;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class InvoiceEndpoint {

    private static final String URI = "http://streetshirt.xyz/soap";
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceEndpoint(InvoiceRepository repository) {this.invoiceRepository = repository;};

    @PayloadRoot(namespace = URI, localPart = "getAllInvoicesRequest")
    @ResponsePayload
    public GetAllInvoicesResponse getAllInvoicesResponse() {
        GetAllInvoicesResponse response = new GetAllInvoicesResponse();
        List<InvoiceInfo> invoiceList = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        for(Invoice inv : invoices) {
            InvoiceInfo info = new InvoiceInfo();
            BeanUtils.copyProperties(inv, info);
            invoiceList.add(info);
        }
        response.getInvoice().addAll(invoiceList);
        return response;
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewInvoice (@RequestParam String company, @RequestParam String service, @RequestParam String date,
                                               @RequestParam Float price) {
        Invoice invoice = new Invoice();
        invoice.setCompany(company);
        invoice.setService(service);
        invoice.setPrice(price);
        invoice.setDate(date);
        invoiceRepository.save(invoice);
        return "Invoice added";
    }
}
