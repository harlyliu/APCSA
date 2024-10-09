					int[] ans = new int[5];
					int[] used = new int[5];
					for (int i = 0; i < 5; i++){
						if (letters.charAt(i) == word.charAt(i)){
							used[i] = 1;
							ans[i] = 2;
						}
					}
					for (int i = 0; i < 5; i++){
						if (ans[i] != 2){
							for (int j = 0; j < 5; j++){
								if (ans[i] == 0 && used[j] == 0 && word.charAt(j)
									== letters.charAt(i)){
									used[j] = 1;
									ans[i] = 1;
									j = 7;
								}
							}
						}
					}
					if (ans[col] == 0){
						StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameDarkGray.png");
					}
					else if (ans[col] == 1){
						StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameYellow.png");
					}
					else{
						StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameGreen.png");
					}
