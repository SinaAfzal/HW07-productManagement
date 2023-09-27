package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.repository.ShareHolderRepository;

public class ShareHolderService {
    private final ShareHolderRepository shareHolderRepository;

    public ShareHolderService(ShareHolderRepository shareHolderRepository) {
        this.shareHolderRepository = shareHolderRepository;
    }
}
