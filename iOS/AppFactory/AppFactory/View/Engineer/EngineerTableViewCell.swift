//
//  EngineerTableViewCell.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerTableViewCell: UITableViewCell {
    
    @IBOutlet private weak var userImageView: UIImageView!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ImageStorage.shared.cancelRequest(imageView: self.userImageView)
    }

    func configure(engineerData: EngineerData) {
        
        let userImageUrl = Constants.ServerRootUrl + Constants.ServerEngineerImageDirectory + engineerData.id + ".png"
        ImageStorage.shared.fetch(url: userImageUrl, imageView: self.userImageView)
    }
}
