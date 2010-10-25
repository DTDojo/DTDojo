//
//  BooleanAlgebraEval.h
//  BooleanCalculator
//
//  Created by Neal Wiggins on 10/23/10.
//

#import <Foundation/Foundation.h>


@interface BooleanAlgebraEval : NSObject {
}
- (id) eval: (NSString *) input;
- (void) evaluateZeroOne: (NSMutableArray *) stack sub: (NSString *) sub;
@end
