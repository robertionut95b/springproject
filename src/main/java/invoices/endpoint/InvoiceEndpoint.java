package invoices.endpoint;

import invoices.repositories.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xyz.streetshirt.soap.GetAllInvoicesResponse;
import xyz.streetshirt.soap.Invoice;
import xyz.streetshirt.soap.InvoiceInfo;

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
}
