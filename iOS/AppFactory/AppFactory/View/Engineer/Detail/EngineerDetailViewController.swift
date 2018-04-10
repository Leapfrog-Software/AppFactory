//
//  EngineerDetailViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerDetailViewController: UIViewController {

    @IBOutlet private weak var tableView: UITableView!

    private var engineerDetailData: EngineerDetailResponseData!
    
    func set(engineerDetailData: EngineerDetailResponseData) {
        self.engineerDetailData = engineerDetailData
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.initTableView()
    }

    private func initTableView() {
        self.tableView.rowHeight = UITableViewAutomaticDimension
        self.tableView.estimatedRowHeight = 200
    }
    
    @IBAction func onTapBack(_ sender: Any) {
        self.pop(animationType: .horizontal)
    }
}

extension EngineerDetailViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2 + self.engineerDetailData.works.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if indexPath.row == 0 {
            let cell = tableView.dequeueReusableCell(withIdentifier: "EngineerDetailProfileTableViewCell", for: indexPath) as! EngineerDetailProfileTableViewCell
            cell.configure(engineerDetailData: self.engineerDetailData!)
            return cell
        } else if indexPath.row == self.engineerDetailData.works.count + 1 {
            return tableView.dequeueReusableCell(withIdentifier: "EngineerDetailPaddingTableViewCell", for: indexPath)
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier: "EngineerDetailWorkTableViewCell", for: indexPath) as! EngineerDetailWorkTableViewCell
            cell.configure(workData: self.engineerDetailData.works[indexPath.row - 1])
            return cell
        }

    }
}
