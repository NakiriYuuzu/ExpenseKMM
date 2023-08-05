//
//  ComposeView.swift
//  iosApp
//
//  Created by yuuzu on 2023/7/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation

struct ComposeView: UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }
}
