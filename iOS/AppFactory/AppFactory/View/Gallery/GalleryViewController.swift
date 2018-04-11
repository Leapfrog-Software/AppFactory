//
//  GalleryViewController.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/10.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class GalleryViewController: UIViewController {

    @IBOutlet private weak var collectionView: UICollectionView!
    
    private var sortType = GallerySortType.evaluate
    
    private var pageIndex: Int?
    private var totalPage: Int?
    private var galleryList = [GalleryData]()
    
    private var isLoading = false
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.fetch()
    }
    
    private func fetch() {
        
        self.isLoading = true
        
        GalleryRequester.get(sort: self.sortType, page: self.pageIndex ?? 0, completion: { response in
            
            self.isLoading = false
            
            if let response = response {
                self.pageIndex = response.page
                self.totalPage = response.total
                response.galleryList.forEach { self.galleryList.append($0) }
                
                self.collectionView.reloadData()
            } else {
                // TODO
            }
        })
    }


}

extension GalleryViewController: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.galleryList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "GalleryCollectionViewCell", for: indexPath) as! GalleryCollectionViewCell
        cell.configuer(galleryData: self.galleryList[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
    }
    
    public func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: UIScreen.main.bounds.size.width / 2, height: 260)
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
