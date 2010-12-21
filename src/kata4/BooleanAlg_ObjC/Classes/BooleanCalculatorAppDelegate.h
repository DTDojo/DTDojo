//
//  BooleanCalculatorAppDelegate.h
//  BooleanCalculator
//
//  Created by Neal Wiggins on 10/23/10.
//

#import <UIKit/UIKit.h>
#import "CalculatorViewController.h"

@interface BooleanCalculatorAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    CalculatorViewController *calcViewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet CalculatorViewController *calcViewController;
@end

