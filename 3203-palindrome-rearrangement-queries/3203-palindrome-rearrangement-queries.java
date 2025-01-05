public class Solution {

	public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
		int[] mismatch = this.matchPalindrome(s);
		if(mismatch.length < 1){
			boolean[] answers = new boolean[queries.length];
			Arrays.fill(answers, true);
			return answers;
		}
		
		int[] cumDist = this.cumulativeDistribution(s);
		StringIndex sIndex = new StringIndex(s, cumDist, mismatch);
		
		int len = queries.length;
		boolean[] answers = new boolean[len];
		for(int i = 0; i < len; i++){
			int[] query = queries[i];
			
			int lowerBegin = query[0];
			int lowerEnd = query[1];
			
			int upperBegin = query[2];
			int upperEnd = query[3];
			
			answers[i] = this.makePalindrome(sIndex, lowerBegin, lowerEnd, upperBegin, upperEnd);
		}
		
		return answers;
	}
	
	protected boolean makePalindrome(StringIndex sIndex, 
			int lowerBegin, int lowerEnd, 
			int upperBegin, int upperEnd) {
		int len = sIndex.str.length();
		int mid = len / 2 + len % 2;
		
		int mirrorBegin = len - 1 - upperEnd;
		int mirrorEnd = len - 1 - upperBegin;
		
		if(!this.checkMismatchNotIn(sIndex, lowerBegin, lowerEnd, mirrorBegin, mirrorEnd)){
			return false;
		}			
		
		int hash = 2*(lowerBegin < mirrorBegin ? -1 : lowerBegin > mirrorEnd ? 1 : 0)
				   + (lowerEnd < mirrorBegin ? -1 : lowerEnd > mirrorEnd ? 1 : 0);
		
		if(lowerBegin <= mirrorBegin && lowerEnd >= mirrorEnd){
			hash = -1;
		}
		
		if(mirrorBegin <= lowerBegin && mirrorEnd >= lowerEnd){
			hash = 0;
		}
		
		switch(hash){
			case -3:
				// [lowerBegin..lowerEnd]..[mirrorBegin..mirrorEnd]
				return this.compareDistribution(sIndex, lowerBegin, lowerEnd)
					&& this.compareDistribution(sIndex, mirrorBegin, mirrorEnd);
		
			case -2:
				// [lowerBegin..mirrorBegin..lowerEnd..mirrorEnd]
				if(!this.compareDistribution(sIndex, lowerBegin, mirrorEnd)){
					return false;
				}
				
				if(mirrorBegin > lowerBegin && mirrorBegin < mid){
					// [lowerBegin..mirrorBegin) must match the other side
					int revBegin = len - mirrorBegin;
					int revEnd = len - 1 - lowerBegin;
					if(!this.compareDistribution(sIndex, lowerBegin, lowerEnd, revBegin, revEnd, 1)) {
						return false;
					}
				}
				
				if(lowerEnd < mirrorEnd && lowerEnd < mid) {
					// (lowerEnd..mirrorEnd] must match the other side
					if(!this.compareDistribution(sIndex, lowerEnd + 1, mirrorEnd, upperBegin, upperEnd, -1)) {
						return false;
					}
				}
				
				return true;
				
			case -1:
				// 0 + (-1): impossible
				// -2 + 1: [lowerBegin..mirrorBegin..mirrorEnd..lowerEnd]
				return this.compareDistribution(sIndex, lowerBegin, lowerEnd);
				
			case 0:
				// [mirrorBegin..lowerBegin..lowerEnd..mirrorEnd]
				return this.compareDistribution(sIndex, mirrorBegin, mirrorEnd);
				
			case 1:
				// [mirrorBegin..lowerBegin..mirrorEnd..lowerEnd]
				if(!this.compareDistribution(sIndex, mirrorBegin, lowerEnd)){
					return false;
				}
				
				if(mirrorBegin < lowerBegin && lowerBegin < mid){
					// [mirrorBegin..lowerBegin) must comes from [upperBegin, upperEnd]
					if(!this.compareDistribution(sIndex, mirrorBegin, lowerBegin - 1, upperBegin, upperEnd, -1)){
						return false;
					}
				}
				
				if(mirrorEnd < lowerEnd && lowerEnd < mid) {
					// ~(mirrorEnd..lowerEnd] must match from [lowerBegin, lowerEnd]					
					int revBegin = len - 1 - lowerEnd;
					int revEnd = len - 1 - (mirrorEnd + 1);
					
					if(!this.compareDistribution(sIndex, lowerBegin, lowerEnd, revBegin, revEnd, 1)){
						return false;
					}
				}
				
				return true;
				
			case 2:
				// 2 + 0: impossible
				break;
				
			case 3:
				// [mirrorBegin..mirrorEnd]..[lowerBegin..lowerEnd]
				return this.compareDistribution(sIndex, mirrorBegin, mirrorEnd)
					&& this.compareDistribution(sIndex, lowerBegin, lowerEnd);
		
			default:
				break;
		}
		
		throw new UnsupportedOperationException("Invalid hash " + hash);
	}
	
	protected boolean compareDistribution(StringIndex sIndex, int begin, int end) {
		int len = sIndex.str.length();
		int mid = len/2 + len%2;
		
		if((begin < mid) != (end < mid)){
			throw new UnsupportedOperationException("Unable to compare [" + begin + "," + end + "] across midpoint " + mid);
		}
		
		int revBegin = len - 1 - end;
		int revEnd = len - 1 - begin;
		
		return this.compareDistribution(sIndex, begin, end, revBegin, revEnd, 0);
	}
	
	protected boolean compareDistribution(StringIndex sIndex, 
			int begin0, int end0, 
			int begin1, int end1, 
			int sgn) {
		
		int base = 'z' - 'a' + 1;
		int[] cumDist = sIndex.cumDist;
		for(int i = 0; i < base; i++){
			int dist0 = cumDist[end0*base + i];
			if(begin0 > 0){
				dist0 -= cumDist[(begin0 - 1)*base + i];
			}
			
			int dist1 = cumDist[end1*base + i];
			if(begin1 > 0){
				dist1 -= cumDist[(begin1 - 1)*base + i];
			}
			
			switch(sgn){
				case -1:
					if(dist0 > dist1){
						return false;
					}
					break;
					
				case 0:
					if(dist0 != dist1){
						return false;
					}
					break;
					
				case 1:
					if(dist0 < dist1) {
						return false;
					}
					break;
			
				default:
					throw new IllegalArgumentException("Invalid comparison " + sgn);
			}
		}
		
		return true;
	}
	
	protected boolean checkMismatchNotIn(StringIndex sIndex, int begin0, int end0, int begin1, int end1) {
		int[] pivots = new int[] {begin0, end0, begin1, end1};
		Arrays.sort(pivots);
		
		int[] mismatch = sIndex.mismatch;
		
		int len = mismatch.length;
		int i = 0;
		
		while(i < len){
			int pos = mismatch[i++];
			if((pos < begin0 || pos > end0)
			&& (pos < begin1 || pos > end1)){
				return false;
			}
			
			if(pos <= pivots[0]){
				i = this.find(mismatch, pos + 1, len, pivots[0]);
				continue;
			}
			
			if(pos <= pivots[1]){
				i = this.find(mismatch, pos + 1, len, pivots[1]);
				continue;
			}
			
			if(pos <= pivots[2]){
				i = this.find(mismatch, pos + 1, len, pivots[2]);
				continue;
			}
			
			if(pos <= pivots[3]){
				i = this.find(mismatch, pos + 1, len, pivots[3]);
				continue;
			}
			
			if(pos > pivots[3]){
				i = len;
			}
		}
		
		return true;
	}
	
	protected int find(int[] array, int begin, int end, int target) {
		if(begin >= end){
			return end;
		}
		
		int lower = begin;
		if(array[lower] > target){
			return lower;
		}
		
		int upper = end - 1;
		if(array[upper] <= target){
			return end;
		}
		
		while(upper - lower > 1){
			int mid = (upper + lower)/2;
			if(array[mid] < target){
				lower = mid;
			}else {
				upper = mid;
			}
		}
		return upper;
	}
	
	protected int[] cumulativeDistribution(String str) {
		int base = 'z' - 'a' + 1;
		
		int len = str.length();
		if(len < 1){
			return new int[0];
		}
		
		int[] dist = new int[base * len];
		char head = str.charAt(0);
		if(head < 'a' || head > 'z'){
			throw new IllegalArgumentException("Invalid character " + head);
		}
		
		dist[head - 'a'] = 1;
		
		for(int i = 1; i < len; i++) {
			char ch = str.charAt(i);
			if(ch < 'a' || ch > 'z'){
				throw new IllegalArgumentException("Invalid character " + ch);
			}
			
			int off = i*base;
			System.arraycopy(dist, off - base, dist, off, base);
			dist[off + (ch - 'a')]++;
		}
		
		return dist;
	}
	
	protected int[] matchPalindrome(String str) {
		int len = str.length();
		int mid = len / 2;
		
		int[] positions = new int[mid];
		int k = 0;
		for(int i = 0; i < mid; i++){
			char ch0 = str.charAt(i);
			char ch1 = str.charAt(len - 1 - i);
			
			if(ch0 != ch1){
				positions[k++] = i;
			}
		}
		
		return Arrays.copyOf(positions, k);
	}

	protected static class StringIndex {

		public final String str;

		public final int[] cumDist;

		public final int[] mismatch;

		public StringIndex(String str, int[] cumDist, int[] mismatch) {
			this.str = str;
			this.cumDist = cumDist;
			this.mismatch = mismatch;
		}

	}

}