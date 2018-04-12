//
//  EngineerViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class EngineerViewController: UIViewController {

    @IBOutlet private weak var tableView: UITableView!
    
    private var isLoading = false
    
    private var keyword = ""
    private var organization = OrganizationType.unspecified
    private var cost = CostType.unsecified
    private var works = WorksType.unspecified
    
    private var pageIndex: Int?
    private var totalPage: Int?
    private var engineerList = [EngineerData]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.initTableView()
        self.fetch()
    }
    
    private func initTableView() {
        self.tableView.rowHeight = UITableViewAutomaticDimension
        self.tableView.estimatedRowHeight = 200
    }
    
    private func fetch() {

        self.isLoading = true
        
        EngineerRequester.get(page: self.pageIndex ?? 0, keyword: self.keyword, organization: self.organization, cost: self.cost, works: self.works, completion: { response in
            
            self.isLoading = false
            
            if let response = response {
                self.pageIndex = response.page
                self.totalPage = response.total
                response.engineerList.forEach { self.engineerList.append($0) }
                
                self.tableView.reloadData()
                
            } else {
                // TODO
            }
        })
    }

    @IBAction func onTapMenu(_ sender: Any) {
        let menu = self.viewController(storyboard: "Engineer", identifier: "EngineerMenuViewController") as! EngineerMenuViewController
        self.tabbarViewController()?.stack(viewController: menu, animationType: .none)
    }

    @IBAction func onTapAllEstimate(_ sender: Any) {
        let estimate = self.viewController(storyboard: "Engineer", identifier: "EstimateViewController") as! EstimateViewController
        estimate.set(targetId: "", targetName: "")
        self.tabbarViewController()?.stack(viewController: estimate, animationType: .horizontal)
    }
}
extension EngineerViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.engineerList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "EngineerTableViewCell", for: indexPath) as! EngineerTableViewCell
        cell.configure(engineerData: self.engineerList[indexPath.row])
        return cell
    }
    
    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        tableView.deselectRow(at: indexPath, animated: true)
        
        let engineerId = self.engineerList[indexPath.row].id
        EngineerDetailRequester.get(id: engineerId, completion: { response in
            if let response = response {
                let detail = self.viewController(storyboard: "Engineer", identifier: "EngineerDetailViewController") as! EngineerDetailViewController
                detail.set(engineerDetailData: response)
                self.tabbarViewController()?.stack(viewController: detail, animationType: .horizontal)
            } else {
                // TODO
            }
        })
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        if self.isLoading {
            return
        }
        if scrollView.contentOffset.y < scrollView.contentSize.height - scrollView.frame.size.height {
            return
        }

        let currentPage = self.pageIndex ?? 0
        let totalPage = self.totalPage ?? 0
        if currentPage < totalPage {
            var pageIndex = self.pageIndex ?? 0
            pageIndex += 1
            self.pageIndex = pageIndex
            
            self.fetch()
        }
    }
}
