package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.request.SendMailClientRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.api.ISendMailServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.ISendMailPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

public class SendMailUseCase implements ISendMailServicePort {

    private final ISendMailPersistencePort iSendMailPersistencePort;
    private final IVehiclePersistencePort iVehiclePersistencePort;
    
    public SendMailUseCase(ISendMailPersistencePort iSendMailPersistencePort,
    		IVehiclePersistencePort iVehiclePersistencePort) {
        this.iSendMailPersistencePort = iSendMailPersistencePort;
        this.iVehiclePersistencePort = iVehiclePersistencePort;
    }

	@Override
	public String sendMail(SendMailRequestDto sendMailRequestDto) {
		SendMailClientRequestDto request = new SendMailClientRequestDto();
		
		VehicleModel vehicleModel; 
		vehicleModel = iVehiclePersistencePort.getByPlate(sendMailRequestDto.getPlate());
		
		if(vehicleModel == null) {
			throw new BadRequestException("No se puede enviar email, la placa no coincide con los vehiculos parqueados");
		} else if(vehicleModel.getStatus().equals(Constants.STATUS_DISABLE)) {
			throw new BadRequestException("No se puede enviar email, la placa no coincide con los vehiculos parqueados");
		} else if (!vehicleModel.getParkingModel().getId().equals(sendMailRequestDto.getParkingId())) {
			throw new BadRequestException("No se puede enviar email, la placa no se encuentra en el parqueadero enviado");
		}
		
		request.setMessage(sendMailRequestDto.getMessage());
		request.setEmail(sendMailRequestDto.getEmail());
		request.setParkingName(vehicleModel.getParkingModel().getName());
		request.setPlate(vehicleModel.getPlate());
		
		return iSendMailPersistencePort.sendMail(request).getMessage();
	}

}