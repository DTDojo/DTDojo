//
//  CalculatorViewController.h
//
//  Created by Neal Wiggins on 10/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "BooleanAlgebraEval.h"

@interface CalculatorViewController :UIViewController {
    BooleanAlgebraEval *evaluator;
    IBOutlet UILabel *testText;
}
- (IBAction) buttonPressed:(UIButton *) sender;
@end
