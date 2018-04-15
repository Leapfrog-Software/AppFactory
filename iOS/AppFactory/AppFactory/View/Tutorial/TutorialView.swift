//
//  TutorialView.swift
//  AppFactory
//
//  Created by Leapfrog-Software on 2018/04/15.
//  Copyright © 2018年 Leapfrog-Inc. All rights reserved.
//

import UIKit

class TutorialView: UIView {
    
    enum ViewType: Int {
        case start = 0
        case cost = 1
        case quality = 2
        case speedy = 3
        case flexibility = 4
        
        static func allValues() -> [ViewType] {
            return [.start, .cost, .quality, .speedy, .flexibility]
        }
    }

    @IBOutlet private weak var titleLabel: UILabel!
    @IBOutlet private weak var imageView: UIImageView!
    @IBOutlet private weak var messageLabel: UILabel!
    
    private var viewType: ViewType!
    
    func set(viewType: ViewType) {
        self.viewType = viewType
        
        self.initContents()
    }
    
    private func initContents() {
        
        let titles = [
            "アプリファクトリーへ\nようこそ！",
            "低価格",
            "高品質",
            "スピーディ",
            "フレキシブル"
        ]
        self.titleLabel.text = titles[self.viewType.rawValue]
        
        let images = [
            UIImage(named: "tutorial1"),
            UIImage(named: "tutorial2"),
            UIImage(named: "tutorial3"),
            UIImage(named: "tutorial4"),
            UIImage(named: "tutorial5")
        ]
        self.imageView.image = images[self.viewType.rawValue]
        
        let messages = [
            "アプリファクトリーは、\nスマホアプリ開発に特化した\nクラウドソーシングサービスです。",
            "一般的な開発会社の開発費と比べ、\n低予算で発注が可能です。",
            "技術力が高く認められた開発者様に\n限定して公開しています。",
            "開発者様に直接依頼する事により\nスムーズにプロジェクトが進行します。",
            "納品後も手厚いサポートにより\n仕様変更や運用保守が行われます。"
        ]
        self.messageLabel.set(text: messages[self.viewType.rawValue], lineHeight: 24)
    }
}
