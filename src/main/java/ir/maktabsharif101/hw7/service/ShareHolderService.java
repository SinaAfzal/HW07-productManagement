package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.entities.ShareHolder;
import ir.maktabsharif101.hw7.repository.ShareHolderRepository;

import java.sql.SQLException;

public class ShareHolderService {
    private final ShareHolderRepository shareHolderRepository;

    public ShareHolderService(ShareHolderRepository shareHolderRepository) {
        this.shareHolderRepository = shareHolderRepository;
    }

    public ShareHolder save(ShareHolder shareHolder) throws SQLException {
        if (shareHolderRepository.doesExist(shareHolder.getNationalCode())) {
            System.out.println("The shareholder already exists on the database!");
            return null;
        } else {
            ShareHolder returnedShareholder = shareHolderRepository.save(shareHolder);
            if (returnedShareholder != null) {
                System.out.println("ID=" + returnedShareholder.getId() + " is assigned to the shareholder and it is stored on database successfully!");
                return returnedShareholder;
            } else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }

    public int updateShareHolderName(String newShareHolderName, int id) throws SQLException {
        if (shareHolderRepository.doesExist(id)) {
            int serverEcho = shareHolderRepository.updateShareHolderName(newShareHolderName, id);
            if (serverEcho > 0)
                System.out.println("Shareholder name was changed successfully!");
            else
                System.out.println("OOPS! Something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Invalid shareholder id!");
            return -1;
        }
    }

}
