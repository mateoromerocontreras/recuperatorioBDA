package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.InvoiceItem;
import ar.utn.edu.recuperatorio.entities.dtos.InvoiceItemDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceItemDtoMapper implements Function<InvoiceItem, InvoiceItemDto> {
    private final InvoiceDtoMapper invoiceDtoMapper;
    private final TrackDtoMapper trackDtoMapper;

    public InvoiceItemDtoMapper(InvoiceDtoMapper invoiceDtoMapper, TrackDtoMapper trackDtoMapper) {
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.trackDtoMapper = trackDtoMapper;
    }

    @Override
    public InvoiceItemDto apply(InvoiceItem i) {

        try {
            return new InvoiceItemDto(
                    i.getId(),
                    invoiceDtoMapper.apply(i.getInvoice()),
                    trackDtoMapper.apply(i.getTrack()),
                    i.getUnitPrice(),
                    i.getQuantity()
            );
        } catch(NullPointerException e) {
            return null;
        }
    }
}
